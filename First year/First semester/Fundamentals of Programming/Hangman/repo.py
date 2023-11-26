from hangman.repo_err import RepoError


class Repo:
    def __init__(self, file_path=str("sentences.txt")):
        self._sentences = []
        self._file_path = file_path

    def _read_data_from_file(self):
        self._sentences = []
        with open(self._file_path, "rt") as file:
            lines = file.readlines()
            for line in lines:
                if line != "":
                    part = line.strip()
                    self._sentences.append(part)
        file.close()

    def _write_data_to_file(self):
        with open(self._file_path, "wt") as file:
            for sentence in self._sentences:
                file.write(str(sentence) + '\n')
        file.close()

    def get_all_sentences_repo(self):
        self._read_data_from_file()
        self._write_data_to_file()
        return self._sentences

    def add_a_sentence_repo(self, sentence):
        self._read_data_from_file()
        if sentence in self._sentences:
            raise RepoError("Sentence already exists!")
        self._sentences.append(sentence)
        self._write_data_to_file()
