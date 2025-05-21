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
$prismUrl = "https://github.com/PrismLauncher/PrismLauncher/releases/download/9.4/PrismLauncher-Windows-MinGW-w64-Portable-9.4.zip"
$prismFolder = Join-Path $userFolder "PrismLauncher"
$installerPath = Join-Path $env:TEMP "PrismLauncher.zip"

$desktopPath = Join-Path $userFolderWeird "Desktop"
$shortcutPath = Join-Path $desktopPath "PrismLauncher.lnk"

if (Test-Path -Path $prismFolder) {
    throw "Minecraft is already installed!"
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

# Download PrismLauncher portable
Write-Host "Downloading PrismLauncher Portable..."
Invoke-WebRequest -Uri $prismUrl -OutFile $installerPath

# Create the target directory
Write-Host "Creating target directory..."
New-Item -Path $prismFolder -ItemType Directory | Out-Null

# Extract PrismLauncher portable to the target directory
Write-Host "Extracting PrismLauncher to "$prismFolder"..."
Invoke-Expression "$7zipPath x $installerPath -o$prismFolder"

# Create desktop shortcut
Write-Host "Creating desktop shortcut..."

$sourceExe = Join-Path $prismFolder "PrismLauncher.exe"

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