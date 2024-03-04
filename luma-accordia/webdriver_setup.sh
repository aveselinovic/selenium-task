#!/bin/bash

# Directory path for drivers
DRIVERS_DIR="drivers"

# Platform for ChromeDriver download
# Detect platform for ChromeDriver download
if [ -n "$RUNNER_OS" ]; then
    if [ "$RUNNER_OS" == "Linux" ]; then
        PLATFORM="linux64"
    elif [ "$RUNNER_OS" == "macOS" ]; then
        PLATFORM="mac-arm64"
    else
        echo "Unsupported platform: $RUNNER_OS"
        exit 1
    fi
else
    OS=$(uname)
    if [ "$OS" == "Darwin" ]; then
        PLATFORM="mac-arm64"
    elif [ "$OS" == "Linux" ]; then
        PLATFORM="linux64"
    else
        echo "Unsupported platform: $OS"
        exit 1
    fi
fi

# Function to clean up existing files in the drivers directory
cleanup_existing_files() {
    if [ -d "$DRIVERS_DIR" ]; then
        echo "Cleaning up existing files in $DRIVERS_DIR..."
        rm -rf "$DRIVERS_DIR"/*
    fi
}

# Function to create directory for drivers
create_drivers_directory() {
    if [ ! -d "$DRIVERS_DIR" ]; then
        echo "Creating directory for drivers..."
        mkdir -p "$DRIVERS_DIR"
    fi
}

# Function to get the latest stable version of Chrome
get_latest_stable_version() {
    local endpoint="https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions.json"
    local response=$(curl -s "$endpoint")
    local stable_version=$(echo "$response" | jq -r '.channels.Stable.version')
    if [ -n "$stable_version" ]; then
        echo "$stable_version"
    else
        echo "Error: Failed to get latest stable version of Chrome."
        exit 1
    fi
}

# Function to construct the download URL for ChromeDriver
construct_download_url() {
    local base_url="https://storage.googleapis.com/chrome-for-testing-public"
    local version="$1"
    local download_url="$base_url/$version/$PLATFORM/chromedriver-$PLATFORM.zip"
    echo "$download_url"
}

# Function to download latest version of ChromeDriver
download_chromedriver() {
    local download_url="$1"
    echo "Downloading ChromeDriver from: $download_url"
    curl -o "$DRIVERS_DIR/chromedriver_mac-arm64.zip" "$download_url"
}

# Function to unzip the downloaded ChromeDriver
unzip_chromedriver() {
    echo "Unzipping ChromeDriver..."
    unzip -qj "$DRIVERS_DIR/chromedriver_mac-arm64.zip" -d "$DRIVERS_DIR/chromedriver"
}

# Function to make chromedriver executable
make_chromedriver_executable() {
    local chromedriver_path="$DRIVERS_DIR/chromedriver/chromedriver"
    if [ -f "$chromedriver_path" ]; then
        chmod +x "$chromedriver_path"
        echo "ChromeDriver is now executable."
    else
        echo "Error: ChromeDriver not found at $chromedriver_path."
    fi
}

# This main func will be run on script execution
main() {
    cleanup_existing_files
    create_drivers_directory
    latest_version=$(get_latest_stable_version)
    download_url=$(construct_download_url "$latest_version")
    if [ -n "$download_url" ]; then
        download_chromedriver "$download_url"
        if [ -f "$DRIVERS_DIR/chromedriver_mac-arm64.zip" ]; then
            unzip_chromedriver
            make_chromedriver_executable
        else
            echo "Error: Failed to download ChromeDriver."
        fi
    else
        echo "Error: Failed to construct download URL."
    fi
}

main
