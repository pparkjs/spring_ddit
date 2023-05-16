package kr.or.ddit.book.dao;

import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {
	
	/*
	 *	Mapper xml을 실행시키기 위해서 SqlsessionTemplate 객체를 멤버 변수로 선언한다.
	 * @Inject 어노테이션을 붙여서 SqlsessionTemplate 객체를 사용할 수 있게 한다. 
	 */
	@Inject
	SqlSessionTemplate sqlSessionTemplate;
	
	/*
	 * sqlSessionTemplate.insert()
	 * 1) 첫번째 파라미터는 SQL Mapper의 id이다.
	 * 		book_SQL.xml에서 namespace로 설정한 'Book'과 insert쿼리를 실행하기 위해 만든 insert문의 id의 값 insert이다.
	 * 		mybatis는 네임스페이스 + id 조합으로 쿼리를 찾아서 실행한다.
	 * 2) 두번째 파라미터는 쿼리에 전달할 데이터이다.
	 * 		mapper 내 insert 쿼리를 실행하기 위해 전달되어 지는 parameterType의 map이다.
	 * 
	 *  외부에서 Dao까지 map에 titlem, category, price가 담겨져서 넘어온다.
	 *  그리고, useGeneratedKey와 keyProperty의 설정으로 book테이블의 pk인 book_id항목을 생성할 수 있다.(mapper xml 참고)
	 */
	public int insertBook(Map<String, Object> map) {
		return sqlSessionTemplate.insert("Book.insert", map);
	}

	public Map<String, Object> selectBook(Map<String, Object> map) {
		/*
		 * sqlSessionTemplate의 selectOne 메소드는 데이터를 한개만가져올 떄 사용한다.
		 * 만약 쿼리 결과 행 수가 0개면 selectOne 메소드는 null을 반환하게 되고,
		 * 쿼리 결과가 여러 개면 ToomanyResultException 예외를 던진다.
		 * 우리가 작성한 쿼리는 조건이 pk이고, pk는 무조건 행이 유일함을 보장하기 때문에 결과는 0개 아니면 1개이다.
		 */
		return sqlSessionTemplate.selectOne("Book.selectBook", map);
	}
}