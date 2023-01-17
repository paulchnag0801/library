package cub.book.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import cub.book.dto.BookDto;
import cub.book.dto.BookQueryRq;
import cub.book.entity.BookEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookEntity> {
	
	@Inject
	EntityManager entityManager;

	public List<BookDto> bookQuery(@Valid BookQueryRq bookQueryRq) {
		//List<BookDto> lsBookDto = new ArrayList<>();
		
		if("1".equals(bookQueryRq.getQueryType())) {
			if(bookQueryRq.getBookIsbn().isEmpty()||bookQueryRq.getBookIsbn()==null) {
				List<BookEntity> lsBookEntity= listAll();
				List<BookDto> lsBookDto = new ArrayList<>();
				for(BookEntity bookEntity: lsBookEntity) {
					BookDto bookDto = new BookDto();
					bookDto.setBookIsbn(bookEntity.getBookIsbn());
					bookDto.setBookLanguage(bookEntity.getBookLanguage());
					bookDto.setBookName(bookEntity.getBookName());
					bookDto.setBookAuthor(bookEntity.getBookAuthor());
					bookDto.setBookPublisher(bookEntity.getBookPublisher());
					bookDto.setBookPubDate(bookEntity.getBookPubDate());
					bookDto.setBookCreateDate(bookEntity.getBookCreateDate());
					bookDto.setBookStatus(bookEntity.getBookStatus());
					bookDto.setBookBorrowerId(bookEntity.getBookBorrowerId());
					bookDto.setBorrowDate(bookEntity.getBorrowDate());
					lsBookDto.add(bookDto);
				}
				return lsBookDto;
			}else {
				PanacheQuery<BookEntity> paBookEntity = find("bookIsbn",bookQueryRq.getBookIsbn());
				Optional<BookEntity> optBookEntity = paBookEntity.singleResultOptional();
				List<BookDto> lsBookDto = new ArrayList<>();
				if(optBookEntity.isPresent()) {
					BookEntity bookEntity= optBookEntity.get();
					BookDto bookDto = new BookDto();
					bookDto.setBookIsbn(bookEntity.getBookIsbn());
					bookDto.setBookLanguage(bookEntity.getBookLanguage());
					bookDto.setBookName(bookEntity.getBookName());
					bookDto.setBookAuthor(bookEntity.getBookAuthor());
					bookDto.setBookPublisher(bookEntity.getBookPublisher());
					bookDto.setBookPubDate(bookEntity.getBookPubDate());
					bookDto.setBookCreateDate(bookEntity.getBookCreateDate());
					bookDto.setBookStatus(bookEntity.getBookStatus());
					bookDto.setBookBorrowerId(bookEntity.getBookBorrowerId());
					bookDto.setBorrowDate(bookEntity.getBorrowDate());
					lsBookDto.add(bookDto);
					return lsBookDto;
				}else {
					return lsBookDto;
				}
			}
		}else if ("2".equals(bookQueryRq.getQueryType())) {
			if("2".equals(bookQueryRq.getBookStatus())) {
				PanacheQuery<BookEntity> paBookEntity = find("bookStatus",bookQueryRq.getBookStatus());
				List<BookEntity> lsBookEntity = paBookEntity.list();
				List<BookDto> lsBookDto = new ArrayList<>();
				for(BookEntity bookEntity: lsBookEntity) {
					BookDto bookDto = new BookDto();
					bookDto.setBookIsbn(bookEntity.getBookIsbn());
					bookDto.setBookLanguage(bookEntity.getBookLanguage());
					bookDto.setBookName(bookEntity.getBookName());
					bookDto.setBookAuthor(bookEntity.getBookAuthor());
					bookDto.setBookPublisher(bookEntity.getBookPublisher());
					bookDto.setBookPubDate(bookEntity.getBookPubDate());
					bookDto.setBookCreateDate(bookEntity.getBookCreateDate());
					bookDto.setBookStatus(bookEntity.getBookStatus());
					bookDto.setBookBorrowerId(bookEntity.getBookBorrowerId());
					bookDto.setBorrowDate(bookEntity.getBorrowDate());
					lsBookDto.add(bookDto);
				}
				return lsBookDto;
			}else if ("3".equals(bookQueryRq.getBookStatus())) {
				PanacheQuery<BookEntity> paBookEntity = find("bookStatus",bookQueryRq.getBookStatus());		
				List<BookEntity> lsBookEntity = paBookEntity.list();
				List<BookDto> lsBookDto = new ArrayList<>();
				for(BookEntity bookEntity: lsBookEntity) {
					BookDto bookDto = new BookDto();
					bookDto.setBookIsbn(bookEntity.getBookIsbn());
					bookDto.setBookLanguage(bookEntity.getBookLanguage());
					bookDto.setBookName(bookEntity.getBookName());
					bookDto.setBookAuthor(bookEntity.getBookAuthor());
					bookDto.setBookPublisher(bookEntity.getBookPublisher());
					bookDto.setBookPubDate(bookEntity.getBookPubDate());
					bookDto.setBookCreateDate(bookEntity.getBookCreateDate());
					bookDto.setBookStatus(bookEntity.getBookStatus());
					bookDto.setBookBorrowerId(bookEntity.getBookBorrowerId());
					bookDto.setBorrowDate(bookEntity.getBorrowDate());
					lsBookDto.add(bookDto);
				}
				return lsBookDto;
			}else {
				List<BookDto> lsBookDto = new ArrayList<>();
				return lsBookDto;
			}
		}else {
			List<BookEntity> lsBookEntity =entityManager
			.createQuery("select e from BookEntity e where e.bookName like ?1", BookEntity.class)
			.setParameter(1, "%"+bookQueryRq.getBookName()+"%")
			.getResultList();
			List<BookDto> lsBookDto = new ArrayList<>();
			for(BookEntity bookEntity: lsBookEntity) {
				BookDto bookDto = new BookDto();
				bookDto.setBookIsbn(bookEntity.getBookIsbn());
				bookDto.setBookLanguage(bookEntity.getBookLanguage());
				bookDto.setBookName(bookEntity.getBookName());
				bookDto.setBookAuthor(bookEntity.getBookAuthor());
				bookDto.setBookPublisher(bookEntity.getBookPublisher());
				bookDto.setBookPubDate(bookEntity.getBookPubDate());
				bookDto.setBookCreateDate(bookEntity.getBookCreateDate());
				bookDto.setBookStatus(bookEntity.getBookStatus());
				bookDto.setBookBorrowerId(bookEntity.getBookBorrowerId());
				bookDto.setBorrowDate(bookEntity.getBorrowDate());
				lsBookDto.add(bookDto);
			}
			return lsBookDto;
		}
	}

}
