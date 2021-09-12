package kz.sab1tm.rest_books_server.repositories;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import kz.sab1tm.rest_books_server.models.author.Author;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sabit Murzaliev on 11.09.2021 14:14
 */

@Repository
public class BooksAuthorsRepository {

    String resource = "ibatis/sql-maps-config.xml";
    Reader reader;
    SqlMapClient sqlmap;

    public BooksAuthorsRepository() {
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlmap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer find(Long bookId, Long authorId) {
        Integer result = 0;
        Map map = new HashMap();
        map.put("book_id", bookId);
        map.put("author_id", authorId);
        try {
            result = (Integer) sqlmap.queryForObject("Books_Authors.findBookIdAndAuthorId", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer insert(Long bookId, Long authorId) {
        Integer result = 0;
        Map map = new HashMap();
        map.put("book_id", bookId);
        map.put("author_id", authorId);
        try {
            if (find(bookId, authorId) == 0)
                sqlmap.insert("Books_Authors.insert", map);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer delete(Long bookId, Long authorId) {
        Integer result = 0;
        Map map = new HashMap();
        map.put("book_id", bookId);
        map.put("author_id", authorId);
        try {
            result = sqlmap.delete("Books_Authors.delete", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}