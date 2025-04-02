package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        Author author = authorRepository.findById(bookCreateDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found"));
        var book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookUpdateDTO, Long id) {
        Author author = authorRepository.findById(bookUpdateDTO.getAuthorId().get())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found"));
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(bookUpdateDTO, book);

        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
