#!/usr/bin/env python3
"""
Daily practice session entrypoint runner.
"""
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from daily_dsa_commit import main

if __name__ == "__main__":
    main()
