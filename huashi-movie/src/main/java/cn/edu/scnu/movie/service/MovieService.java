package cn.edu.scnu.movie.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.scnu.movie.mapper.MovieMapper;
import cn.edu.scnu.pojo.Movie;
import cn.edu.scnu.utils.PrefixKey;
import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisPool;

@Service
public class MovieService {

	@Autowired
	private MovieMapper moviemapper;
	@Autowired
	private JedisCluster jedis;
//	@Autowired
//	private ShardedJedisPool pool;
	
	public List<Movie> searchMovie(String movieName,Integer start,Integer length) {
		// TODO Auto-generated method stub
		List<Movie> mlist= moviemapper.searchMovie(movieName,start,length);	
		return mlist;
	}

	public Integer getMovieNum(String movieName) {
		// TODO Auto-generated method stub
		return moviemapper.getMovieNum(movieName);
	}
	
	public Integer getTotal(){
		return moviemapper.getTotal();
	}
	
	public List<Movie> getAllMovie(Integer start, Integer length){
		return moviemapper.getAllMovie(start,length);
	}

	public List<String> getCategory() {
		// TODO Auto-generated method stub
		List<String> cate=moviemapper.getCategory();
		HashSet<String> hashset=new HashSet<>();
		for(int i=0;i<cate.size();i++){
			String[] str=cate.get(i).split(",");
			for(int j=0;j<str.length;j++){
				String temp=str[j].replace(" ","");
				hashset.add(temp);
			}
		}
		List<String> category=new ArrayList<String>(hashset);
		return category;
	}

	public List<Movie> searchByCategory(String movieCate, Integer start, Integer length) {
		// TODO Auto-generated method stub
		return moviemapper.searchByCategory(movieCate,start,length);
	}

	public Integer getPageByCategory(String movieCate) {
		// TODO Auto-generated method stub
		return moviemapper.getPageByCategory(movieCate);
	}

	public Movie showMovieDetail(Integer movieId) {
		// TODO Auto-generated method stub
		ObjectMapper mapper=new ObjectMapper();
		String id=PrefixKey.MOVIE_PREFIX+Integer.toString(movieId);
//		ShardedJedis jedis = pool.getResource();
		if(jedis.get(id)!=null){
			String json=jedis.get(id);
			Movie movie = null;
			try {
				movie = mapper.readValue(json,Movie.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movie;
		}else{
			Movie movie=moviemapper.showMovieDetail(movieId);
			String moviejson = null;
			try {
				moviejson = mapper.writeValueAsString(movie);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			jedis.set(id, moviejson,"NX","EX",600);
			return movie;
		}
	}

	public List<Movie> getHotMovie() {
		// TODO Auto-generated method stub
		return moviemapper.getHotMovie();
	}

	public Integer getActorId(String actorName) {
		// TODO Auto-generated method stub
		return moviemapper.getActorId(actorName);
	}


}
