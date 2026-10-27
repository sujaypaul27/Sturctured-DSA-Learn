# 🚀 Structured DSA Learn (Java)

Autonomous daily Data Structures & Algorithms learning journey in Java. Powered by **Google Gemini** (`google-genai` SDK) and **GitHub Actions**, this repository autonomously solves, documents, tests, and commits 4–5 structured Java DSA problems every evening.

---

## 📅 Nightly Autonomous Routine

Every day at **8:30 PM IST (15:00 UTC)**, a scheduled GitHub Actions workflow triggers automatically:

1. **Scans Existing Progress:** Reads all `.java` files in [`DSAprogress/`](./DSAprogress/) and the progress tracker [`DSAprogress/progress_tracker.json`](./DSAprogress/progress_tracker.json) to eliminate duplicate problems.
2. **Follows Curriculum Progression:** Selects the next **4 to 5 problems** in sequence from a 9-module DSA syllabus.
3. **Generates Educational Java Code:** Uses Gemini (`gemini-2.5-flash`) via the `google-genai` SDK with strict Pydantic schemas. Each program includes:
   - Full Javadoc header with **Problem Statement**, **Examples**, **Intuition/Approach**, and **Time & Space Complexity**.
   - Public class `<ClassName>` matching `<ClassName>.java`.
   - Comprehensive `public static void main(String[] args)` with 2–3 test cases and formatted console outputs.
4. **Validates Syntax:** Compiles each file with `javac` and cleans up `.class` bytecode artifacts immediately.
5. **Simulates Natural Evening Coding Sessions:** Assigns sorted, randomized commit timestamps between **8:30 PM and 11:55 PM IST** (`+05:30`).
6. **Separate Commits & Pushes:** Commits and pushes each problem individually with a human-like delay between pushes, updating the GitHub contribution graph with authentic green squares.

---

## 📚 DSA Curriculum Roadmap

| Module | Topic | Core Focus |
| :---: | :--- | :--- |
| **1** | **Basic Math & Number Logic** | Digit extraction, prime tests, Armstrong numbers, GCD/LCM, palindrome numbers |
| **2** | **Patterns & Recursion Basics** | Recursive problem solving, functional recursion, Tower of Hanoi, subsets |
| **3** | **Arrays - Easy to Medium** | Two pointers, Kadane's algorithm, Dutch National Flag, matrix operations |
| **4** | **Strings** | Anagrams, palindrome verification, sliding window, frequency hashing |
| **5** | **Searching & Sorting** | Binary search variants, merge sort, quicksort, rotated sorted arrays |
| **6** | **Linked Lists** | Cycle detection (Floyd's algorithm), reversal, merge operations |
| **7** | **Stacks & Queues** | Monotonic stacks, next greater elements, parentheses validation |
| **8** | **Binary Trees & BST** | Traversals, depth & diameter, balanced trees, BST lookups & deletions |
| **9** | **Heaps, Graphs & DP** | Priority queues, BFS/DFS, Dijkstra, topological sort, 1D & 2D Dynamic Programming |

---

## 🛠️ Repository Structure

```text
Sturctured-DSA-Learn/
├── .github/
│   └── workflows/
│       └── nightly_dsa_routine.yml   # Daily cron (8:30 PM IST) & manual workflow_dispatch
├── DSAprogress/
│   ├── progress_tracker.json         # Automated log of solved topics & timestamps
│   └── <ClassName>.java              # Educational Java DSA source files
├── scripts/
│   ├── daily_dsa_commit.py           # Primary autonomous routine script
│   └── daily_dsa_agent.py            # Alias runner wrapper
├── requirements.txt                  # Python dependencies (google-genai, pydantic)
├── .gitignore                        # Bytecode, cache, and env ignore rules
└── README.md                         # Documentation
```

---

## ⚙️ Configuration & Secrets

### 1. GitHub Repository Secret
The GitHub Actions workflow requires a Gemini API key:
- Navigate to: **Settings** &rarr; **Secrets and variables** &rarr; **Actions**
- Click **New repository secret**
- Name: `GEMINI_API_KEY`
- Value: `<your-google-ai-studio-api-key>`

### 2. GitHub Actions Permissions
Ensure the workflow has write access to push commits:
- Navigate to: **Settings** &rarr; **Actions** &rarr; **General**
- Under **Workflow permissions**, choose **"Read and write permissions"**
- Click **Save**

---

## 💻 Manual & Local Execution

### Install Dependencies
```bash
pip install -r requirements.txt
```

### Dry Run (Test without committing or pushing)
```bash
python scripts/daily_dsa_commit.py --dry-run
```

### Force a Specific Count (4 or 5)
```bash
python scripts/daily_dsa_commit.py --count 5
```

### Run Locally with Full Commit & Push
```bash
export GEMINI_API_KEY="your-api-key"
python scripts/daily_dsa_commit.py
```

*(On Windows PowerShell):*
```powershell
$env:GEMINI_API_KEY="your-api-key"
python scripts/daily_dsa_commit.py
```

---

## 👤 Author

- **Name:** Sujay Paul ([@sujaypaul27](https://github.com/sujaypaul27))
- **Email:** `sujaypaul2711@gmail.com`