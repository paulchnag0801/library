package cub.book.service.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import cub.book.dto.BookDto;
import cub.book.dto.BookQueryRq;
import cub.book.dto.BookQueryRs;
import cub.book.dto.BookUpdateRq;
import cub.book.dto.base.CubResponse;
import cub.book.entity.BookEntity;
import cub.book.enums.ReturnCodeEnum;
import cub.book.mapper.BookMapper;
import cub.book.repository.BookRepository;
import cub.book.service.BookService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class BookServiceImpl implements BookService {

	@Inject
	BookMapper bookMapper;	
	
	private BookRepository bookRepository;
	
	@Inject
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public CubResponse<BookQueryRs> bookQuery(@Valid BookQueryRq bookQueryRq) {
		List<BookDto> lsBookDto = bookRepository.bookQuery(bookQueryRq);
		BookQueryRs bookQueryRs = new BookQueryRs();
		bookQueryRs.setBookCount(lsBookDto.size());
		bookQueryRs.setBookList(lsBookDto);
		CubResponse<BookQueryRs> cubRs = new CubResponse<BookQueryRs>();
		cubRs.setReturnCodeAndDesc(ReturnCodeEnum.SUCCESS);
		cubRs.setTranRs(bookQueryRs);
		
		return cubRs;
	}

	@Override
	@Transactional
	public CubResponse<BookUpdateRq> bookUpdate(@Valid BookUpdateRq bookUpdateRq) {
		
		CubResponse<BookUpdateRq> cubRs = new CubResponse<BookUpdateRq>();
		
		PanacheQuery<BookEntity> paBookEntity = bookRepository.find("bookIsbn",bookUpdateRq.getBookIsbn());
		Optional<BookEntity> optBookEntity = paBookEntity.singleResultOptional();
		if(optBookEntity.isPresent()) {
			BookEntity bookEntity= optBookEntity.get();
			bookMapper.BookUpdateRqToBookEntity(bookUpdateRq, bookEntity);
			bookRepository.persist(bookEntity);	
			cubRs.setReturnCodeAndDesc(ReturnCodeEnum.SUCCESS);		
			return cubRs;
		}		
		cubRs.setReturnCodeAndDesc(ReturnCodeEnum.E001);		
		return cubRs;
	}
	
	

}
