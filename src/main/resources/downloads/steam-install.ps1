$user = [Environment]::GetFolderPath("UserProfile")
$root = Join-Path $user "RLHSUtils"
$installs = Join-Path $root "installs"
$libs = Join-Path $root "libs"
$desktop = "\\internal.rotorualakes.school.nz\Users\Home\Students\$env:username\Desktop"

if (!(Test-Path -Path $root)) {
    # Run Setup Script
    Write-Host "Running setup script..."

    $setupUrl = "https://raw.githubusercontent.com/Apollointhehouse/Apollointhehouse-Web/refs/heads/master/src/main/resources/downloads/setup.ps1"
    $setup = Join-Path $env:TEMP "setup.ps1"
    Invoke-WebRequest -Uri $setupUrl -OutFile $setup
    Invoke-Expression $setup

    Remove-Item $setup -Force
}

$url = "https://cdn.cloudflare.steamstatic.com/client/installer/SteamSetup.exe"
$installer = Join-Path $env:TEMP "SteamSetup.exe"
$steam = Join-Path $installs "Steam"
$shortcut = Join-Path $desktopPath "Steam.lnk"

if (Test-Path -Path $steam) {
    throw "Steam is already installed!"
}

# Download Steam installer
Write-Host "Downloading Steam installer..."
Invoke-WebRequest -Uri $url -OutFile $installer

# Create  target directory
Write-Host "Creating target directory..."
New-Item -Path $steam -ItemType Directory | Out-Null

# Extract Steam to the target directory
Write-Host "Extracting steam to "$steam"..."
Invoke-Expression "$libs/7zip/7z.exe x $installer -o$steam"

# Create desktop shortcut
Write-Host "Creating desktop shortcut..."

$sourceExe = Join-Path $steam "Steam.exe"

$WshShell = New-Object -COMObject WScript.Shell
$Shortcut = $WshShell.CreateShortcut($shortcut)
$Shortcut.TargetPath = $sourceExe
$Shortcut.Save()

# Cleanup
Write-Host "Cleaning up..."
Remove-Item $installer -Force

Write-Host "Done!"
Read-Host -Prompt "Press Enter to exit"