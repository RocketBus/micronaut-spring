#!/bin/bash

# Set default values if not provided
DURATION=${DURATION:-2m}
USERS=${USERS:-1000}
SPAWN_RATE=${SPAWN_RATE:-100}
TARGET_HOST=${TARGET_HOST:-http://host.docker.internal:8080}

# Create results directory with timestamp
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
RESULTS_DIR="/locust/results/$TIMESTAMP"
mkdir -p "$RESULTS_DIR"

echo "Starting Locust test with the following parameters:"
echo "- Duration: $DURATION"
echo "- Users: $USERS"
echo "- Spawn rate: $SPAWN_RATE"
echo "- Target host: $TARGET_HOST"
echo "- Results directory: $RESULTS_DIR"

# Run Locust test
locust \
  --headless \
  --users $USERS \
  --spawn-rate $SPAWN_RATE \
  --run-time $DURATION \
  --host $TARGET_HOST \
  --csv "$RESULTS_DIR/stats" \
  --html "$RESULTS_DIR/report.html" \
  --logfile "$RESULTS_DIR/locust.log"

# Generate a summary
echo "\nTest completed. Results saved to $RESULTS_DIR"

# Exit with the exit code from locust
exit $?
