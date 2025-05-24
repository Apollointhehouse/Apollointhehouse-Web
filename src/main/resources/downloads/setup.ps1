$user = [Environment]::GetFolderPath("UserProfile")
$root = Join-Path $user "RLHSUtils"
$installs = Join-Path $root "installs"
$libs = Join-Path $root "libs"

# Ensure the root, installs, and libs directories exist
if (!(Test-Path -Path $root)) { New-Item -Path $root -ItemType Directory | Out-Null }
if (!(Test-Path -Path $installs)) { New-Item -Path $installs -ItemType Directory | Out-Null }
if (!(Test-Path -Path $libs)) { New-Item -Path $libs -ItemType Directory | Out-Null }

# Ensure 7zip console is installed
$7zConsole = Join-Path $libs "7zr.exe"

if (!(Test-Path -Path $7zConsole)) {
    $7zConsoleUrl = "https://www.7-zip.org/a/7zr.exe"

    # Download 7zip console
    Write-Host "Downloading 7zip console..."
    Invoke-WebRequest -Uri $7zConsoleUrl -OutFile $7zConsole
}


# Ensure 7zip is installed
$7z = Join-Path $libs "7zip"

if (!(Test-Path -Path $7z)) {
    $7zUrl = "https://www.7-zip.org/a/7z2409-x64.exe"
    $7zInstaller = Join-Path $env:TEMP "7z-installer.exe"

    # Download 7zip
    Write-Host "Downloading 7zip..."
    Invoke-WebRequest -Uri $7zUrl -OutFile $7zInstaller

    # Extract 7Zip
    New-Item -Path $7z -ItemType Directory | Out-Null
    Invoke-Expression "$7zConsole x $7zInstaller -o$7z"

    # Cleanup
    Remove-Item $7zInstaller -Force
}