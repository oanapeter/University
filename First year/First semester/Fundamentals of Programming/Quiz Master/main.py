from repo import Repo
from service import Service
from ui import UI
from validator import Validator


def main():
    repo = Repo()
    validator = Validator()
    service = Service(repo, validator)
    ui = UI(service)
    ui.menu()

main()
