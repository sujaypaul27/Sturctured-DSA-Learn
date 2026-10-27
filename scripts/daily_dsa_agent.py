#!/usr/bin/env python3
"""
Alias entrypoint for daily_dsa_commit.py.
Allows running via either `python scripts/daily_dsa_agent.py` or `python scripts/daily_dsa_commit.py`.
"""
import sys
from pathlib import Path

# Ensure scripts directory is on sys.path
sys.path.insert(0, str(Path(__file__).resolve().parent))

from daily_dsa_commit import main

if __name__ == "__main__":
    main()
