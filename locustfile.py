from locust import HttpUser, task, between
from faker import Faker
import random
import json
import os

class LogsUser(HttpUser):
    wait_time = between(0.5, 2.5)
    fake = Faker()
    
    @task
    def post_logs(self):
        # Generate random log data
        log_data = {
            "value": self.fake.sentence()
        }
        
        headers = {'Content-Type': 'application/json'}
        self.client.post(
            "/api/logs",
            data=json.dumps(log_data),
            headers=headers,
            name="/api/logs"
        )

# Add custom arguments
def get_locust_stage_override():
    return {
        "duration": os.getenv("DURATION", "2m"),
        "users": int(os.getenv("USERS", "1000")),
        "spawn_rate": int(os.getenv("SPAWN_RATE", "100")),
        "host": os.getenv("TARGET_HOST", "http://host.docker.internal:8080")
    }
