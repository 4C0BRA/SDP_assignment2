#!/usr/bin/env bash
# Compiles the project and runs the six required checks plus the missing-input cases.
# Usage: ./scripts/run_checks.sh   (run from the project root)
set -u
cd "$(dirname "$0")/.."

rm -rf out && mkdir out
javac --release 17 -d out $(find src -name '*.java') || exit 1

run() {
  local title="$1"; shift
  echo "=== $title"
  echo "\$ java -cp out com.assignment2.app.Main $*"
  java -cp out com.assignment2.app.Main "$@"
  echo "[exit code: $?]"
  echo
}

run "Check 1: ROAD + WINDOWS" ROAD WINDOWS
run "Check 2: SEA + WINDOWS" SEA WINDOWS
run "Check 3: ROAD + MACOS" ROAD MACOS
run "Check 4: SEA + MACOS" SEA MACOS
run "Check 5: unsupported delivery mode, valid platform" AIR WINDOWS
run "Check 6: unsupported platform, valid delivery mode" ROAD LINUX
run "Missing input: only one argument" ROAD

echo "=== Missing input: no arguments, empty stdin"
echo "\$ java -cp out com.assignment2.app.Main < /dev/null"
java -cp out com.assignment2.app.Main < /dev/null
echo "[exit code: $?]"
echo

echo "=== Interactive: invalid answer, then valid answers"
echo "\$ printf 'boat\nsea\nmacos\n' | java -cp out com.assignment2.app.Main"
printf 'boat\nsea\nmacos\n' | java -cp out com.assignment2.app.Main
echo "[exit code: $?]"
