$user = [Environment]::GetFolderPath("UserProfile")
$root = Join-Path $user "RLHSUtils"
$installs = Join-Path $root "installs"
$libs = Join-Path $root "libs"
$desktop = "\\internal.rotorualakes.school.nz\Users\Home\Students\$env:username\Desktop"
#$desktop = "C:\Users\apoll\Desktop"

# Run Setup Script
Write-Host "Running setup script..."

$setupUrl = "https://raw.githubusercontent.com/Apollointhehouse/Apollointhehouse-Web/refs/heads/master/src/main/resources/downloads/setup.ps1"
$setup = Join-Path $env:TEMP "setup.ps1"
Invoke-WebRequest -Uri $setupUrl -OutFile $setup
Invoke-Expression $setup

Remove-Item $setup -Force

$url = "https://github.com/PrismLauncher/PrismLauncher/releases/download/9.4/PrismLauncher-Windows-MinGW-w64-Portable-9.4.zip"
$installer = Join-Path $env:TEMP "PrismLauncher.zip"
$prism = Join-Path $installs "PrismLauncher"
$shortcut = Join-Path $desktop "PrismLauncher.lnk"

if (Test-Path -Path $prism) {
    throw "Minecraft is already installed!"
}

# Download PrismLauncher portable
Write-Host "Downloading PrismLauncher Portable..."
Invoke-WebRequest -Uri $url -OutFile $installer

# Create target directory
Write-Host "Creating target directory..."
New-Item -Path $prism -ItemType Directory | Out-Null

# Extract PrismLauncher portable to the target directory
Write-Host "Extracting PrismLauncher to "$prism"..."
Invoke-Expression "$libs/7zip/7z.exe x $installer -o$prism"

# Create desktop shortcut
Write-Host "Creating desktop shortcut..."

$sourceExe = Join-Path $prism "PrismLauncher.exe"

$WshShell = New-Object -COMObject WScript.Shell
$Shortcut = $WshShell.CreateShortcut($shortcut)
$Shortcut.TargetPath = $sourceExe
$Shortcut.Save()

# Cleanup
Write-Host "Cleaning up..."
Remove-Item $installer -Force

Write-Host "Done!"
Read-Host -Prompt "Press Enter to exit"