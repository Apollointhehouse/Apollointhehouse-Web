# Define variables

$7zipConsoleUrl = "https://www.7-zip.org/a/7zr.exe"
$7zipConsolePath = Join-Path $env:TEMP "7zr.exe"

$7zipUrl = "https://www.7-zip.org/a/7z2409-x64.exe"
$7zipInstallerPath = Join-Path $env:TEMP "7z-installer.exe"
$7zipFolder = Join-Path $env:TEMP "7zip"
$7zipPath = Join-Path $env:TEMP "7zip/7z.exe"

$userFolder = [Environment]::GetFolderPath("UserProfile")
$userFolderWeird = Join-Path "\\internal.rotorualakes.school.nz\Users\Home\Students\" $env:username
# $userFolderWeird = $userFolder
$steamUrl = "https://cdn.cloudflare.steamstatic.com/client/installer/SteamSetup.exe"
$steamFolder = Join-Path $userFolder "Steam"
$installerPath = Join-Path $env:TEMP "SteamSetup.exe"

$desktopPath = Join-Path $userFolderWeird "Desktop"
$shortcutPath = Join-Path $desktopPath "Steam.lnk"

if (Test-Path -Path $steamFolder) {
    throw "Steam is already installed!"
}

# Download 7zip console
Write-Host "Downloading 7zip console..."
Invoke-WebRequest -Uri $7zipConsoleUrl -OutFile $7zipConsolePath

# Download 7zip
Write-Host "Downloading 7zip..."
Invoke-WebRequest -Uri $7zipUrl -OutFile $7zipInstallerPath

# Extract 7Zip using 7Zip to 7Zip directory
New-Item -Path $7zipFolder -ItemType Directory | Out-Null
Invoke-Expression "$7zipConsolePath x $7zipInstallerPath -o$7zipFolder"

# Download the Steam installer
Write-Host "Downloading Steam installer..."
Invoke-WebRequest -Uri $steamUrl -OutFile $installerPath

# Create the target directory
Write-Host "Creating target directory..."
New-Item -Path $steamFolder -ItemType Directory | Out-Null

# Extract Steam to the target directory
Write-Host "Extracting steam to "$steamFolder"..."
Invoke-Expression "$7zipPath x $installerPath -o$steamFolder"

# Create desktop shortcut
Write-Host "Creating desktop shortcut..."

$sourceExe = Join-Path $steamFolder "Steam.exe"

$WshShell = New-Object -COMObject WScript.Shell
$Shortcut = $WshShell.CreateShortcut($shortcutPath)
$Shortcut.TargetPath = $sourceExe
$Shortcut.Save()

# Cleanup
Write-Host "Cleaning up..."
Remove-Item $installerPath -Force
Remove-Item $7zipConsolePath -Force
Remove-Item $7zipInstallerPath -Force
Remove-Item $7zipFolder -Force -Recurse

Write-Host "Done!"
Read-Host -Prompt "Press Enter to exit"