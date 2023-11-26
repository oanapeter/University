from hangman.repo import Repo
from hangman.service import Service
from hangman.ui import UI
from hangman.validate import Validate


def main():
    repo = Repo()
    validator = Validate()
    service = Service(repo, validator)
    ui = UI(service)
    ui.start()

main()
