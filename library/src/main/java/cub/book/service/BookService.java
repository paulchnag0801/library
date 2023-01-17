package cub.book.service;

import javax.validation.Valid;

import cub.book.dto.BookQueryRq;
import cub.book.dto.BookQueryRs;
import cub.book.dto.base.CubResponse;

public interface BookService {

	CubResponse<BookQueryRs> bookQuery(@Valid BookQueryRq bookQueryRq);

}
