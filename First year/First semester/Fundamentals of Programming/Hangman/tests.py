import unittest

from hangman.repo import Repo
from hangman.repo_err import RepoError
from hangman.service import Service
from hangman.validate import Validate


class TestHangman(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_add_sentence(self):
        validate_sentence = Validate()
        repo = Repo()
        service = Service(repo, validate_sentence)
        repo._write_data_to_file()
        service.add_a_sentence_service("oana has apples")
        self.assertEqual(len(service.return_sentences()), 1)
        try:
            service.add_a_sentence_service("oana has apples")
            assert False
        except RepoError as re:
            self.assertEqual(str(re), "Sentence already exists!")

    def test_play(self):
        validate_sentence = Validate()
        repo = Repo()
        service = Service(repo, validate_sentence)
        repo._write_data_to_file()
        service.add_a_sentence_service("oana has apples")
        sentence = service.choose_a_sentence_service()
        list_with_characters = list(sentence)
        list_with_encoded_characters = service.encode_the_sentence(list_with_characters)
        self.assertEqual(list_with_encoded_characters,
                         ['o', '_', '_', '_', ' ', '_', '_', 's', ' ', '_', '_', '_', '_', '_', 's'])
