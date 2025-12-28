package cn.edu.scnu.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.scnu.pojo.Movie;

public interface MovieMapper {

	List<Movie> searchMovie(@Param("movieName")String movieName,@Param("start")Integer start,@Param("length")Integer length);

	Integer getMovieNum(String movieName);
		
	Integer getTotal();

	List<Movie> getAllMovie(@Param("start")Integer start,@Param("length")Integer length);

	List<String> getCategory();

	List<Movie> searchByCategory(@Param("movieCate")String movieCate,@Param("start") Integer start,@Param("length") Integer length);

	Integer getPageByCategory(String movieCate);

	Movie showMovieDetail(Integer movieId);

	List<Movie> getHotMovie();

	Integer getActorId(String actorName);
	
	
}
