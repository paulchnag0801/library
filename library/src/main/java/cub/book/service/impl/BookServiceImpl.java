package cub.book.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import cub.book.dto.BookDto;
import cub.book.dto.BookQueryRq;
import cub.book.dto.BookQueryRs;
import cub.book.dto.base.CubResponse;
import cub.book.enums.ReturnCodeEnum;
import cub.book.repository.BookRepository;
import cub.book.service.BookService;

@ApplicationScoped
public class BookServiceImpl implements BookService {
	
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

}
