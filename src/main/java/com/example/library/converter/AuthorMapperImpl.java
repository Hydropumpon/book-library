//package com.example.library.converter;
//
//import com.example.library.dto.AuthorDto;
//import com.example.library.dto.BookDto;
//import com.example.library.model.Author;
//import com.example.library.model.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class AuthorMapperImpl implements Mapper<Author, AuthorDto>
//{
//	@Autowired
//	private BookConverter bookConverter;
//
//	public Author fromDto(AuthorDto dto, CycleAvoidingMappingContext context) {
//		Author target = context.getMappedInstance( dto, Author.class );
//		if ( target != null ) {
//			return target;
//		}
//
//		if ( dto == null ) {
//			return null;
//		}
//
//		Author author = new Author();
//
//		context.storeMappedInstance( dto, author );
//
//		author.setId( dto.getId() );
//		author.setName( dto.getName() );
//		author.setBooks( bookDtoSetToBookSet( dto.getBooks(), context ) );
//
//		return author;
//	}
//
//
//	public AuthorDto toDto(Author entity, CycleAvoidingMappingContext context) {
//		AuthorDto target = context.getMappedInstance( entity, AuthorDto.class );
//		if ( target != null ) {
//			return target;
//		}
//
//		if ( entity == null ) {
//			return null;
//		}
//
//		AuthorDto authorDto = new AuthorDto();
//
//		context.storeMappedInstance( entity, authorDto );
//
//		authorDto.setId( entity.getId() );
//		authorDto.setName( entity.getName() );
//		authorDto.setBooks( bookSetToBookDtoSet( entity.getBooks(), context ) );
//
//		return authorDto;
//	}
//
//	protected Set<Book> bookDtoSetToBookSet(Set<BookDto> set, CycleAvoidingMappingContext context) {
//		if ( set == null ) {
//			return null;
//		}
//
//		Set<Book> set1 = new HashSet<Book>(Math.max((int) ( set.size() / .75f ) + 1, 16 ) );
//		context.storeMappedInstance( set, set1 );
//
//		for ( BookDto bookDto : set ) {
//			set1.add( bookConverter.fromDto( bookDto, context ) );
//		}
//
//		return set1;
//	}
//
//	protected Set<BookDto> bookSetToBookDtoSet(Set<Book> set, CycleAvoidingMappingContext context) {
//		if ( set == null ) {
//			return null;
//		}
//
//		Set<BookDto> set1 = new HashSet<BookDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
//		context.storeMappedInstance( set, set1 );
//
//		for ( Book book : set ) {
//			set1.add( bookConverter.toDto( book, context ) );
//		}
//
//		return set1;
//	}
//
//
//	@Override
//	public AuthorDto toDto(Author author)
//	{
//
//	}
//
//	@Override
//	public Author fromDto(AuthorDto authorDto)
//	{
//		Author author = new Author();
//
//		author.setId(authorDto.getId());
//		author.setName(authorDto.getName());
//
//	}
//}
