$user = [Environment]::GetFolderPath("UserProfile")
$root = Join-Path $user "RLHSUtils"
$installs = Join-Path $root "installs"
$libs = Join-Path $root "libs"

New-Item -Path $root -ItemType Directory | Out-Null
New-Item -Path $installs -ItemType Directory | Out-Null
New-Item -Path $libs -ItemType Directory | Out-Null

$7zConsoleUrl = "https://www.7-zip.org/a/7zr.exe"
$7zConsole = Join-Path $env:TEMP "7zr.exe"

$7zUrl = "https://www.7-zip.org/a/7z2409-x64.exe"
$7zInstaller = Join-Path $env:TEMP "7z-installer.exe"
$7z = Join-Path $libs "7zip"

# Download 7zip console
Write-Host "Downloading 7zip console..."
Invoke-WebRequest -Uri $7zConsoleUrl -OutFile $7zConsole

# Download 7zip
Write-Host "Downloading 7zip..."
Invoke-WebRequest -Uri $7zUrl -OutFile $7zInstaller

# Extract 7Zip
New-Item -Path $7z -ItemType Directory | Out-Null
Invoke-Expression "$7zConsole x $7zInstaller -o$7z"

# Cleanup
Write-Host "Cleaning up..."
Remove-Item $7zConsole -Force
Remove-Item $7zInstaller -Force