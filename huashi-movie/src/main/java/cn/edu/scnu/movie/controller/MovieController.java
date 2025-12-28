package cn.edu.scnu.movie.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.movie.service.MovieService;
import cn.edu.scnu.pojo.Movie;
import cn.edu.scnu.vo.SysResult;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieservice;
	
	//按电影名查找电影
	@RequestMapping("/movie/manage/searchmovie")
	public SysResult searchMovie(String movieName,Integer start, Integer length){
		List<Movie> mlistjson=movieservice.searchMovie(movieName,start,length);
		if(mlistjson.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", mlistjson);
		}
	}
	
	//按电影名查找电影得到分页总数
	@RequestMapping("/movie/manage/getPage/{movieName}")
	public SysResult getPage(@PathVariable String movieName){
		Integer a=movieservice.getMovieNum(movieName);
		Integer extra=a%9;
		Integer pagenum=a/9+(extra>0?1:0);
		if(a>0){
			return SysResult.build(200, "ok", pagenum);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	//得到电影总数
	@RequestMapping("/movie/manage/getTotal")
	public SysResult getTotal(){
		Integer a=movieservice.getTotal();
		Integer extra=a%9;
		Integer pagenum=a/9+(extra>0?1:0);
		if(a>0){
			return SysResult.build(200, "ok", pagenum);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	
	//得到所有的电影
	@RequestMapping("/movie/manage/getAllMovie")
	public SysResult getAllMovie(Integer start, Integer length){
		List<Movie> mlistjson=movieservice.getAllMovie(start,length);
		if(mlistjson.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", mlistjson);
		}
	}
	//得到种类
	@RequestMapping("/movie/manage/getCategory")
	public SysResult getCategory(){
		List<String> category=movieservice.getCategory();
		if(category.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", category);
		}
	}
	//根据分类查找电影
	@RequestMapping("movie/manage/searchByCategory")
	public SysResult searchByCategory(String movieCate,Integer start, Integer length){
		List<Movie> mlistjson=movieservice.searchByCategory(movieCate,start,length);
		if(mlistjson.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", mlistjson);
		}
	}
	
	//根据分类得到分类总数
	@RequestMapping("movie/manage/getPageByCategory/{movieCate}")
	public SysResult getPageByCategory(@PathVariable String movieCate){
		Integer a=movieservice.getPageByCategory(movieCate);
		Integer extra=a%9;
		Integer pagenum=a/9+(extra>0?1:0);
		if(a>0){
			return SysResult.build(200, "ok", pagenum);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	
	//展示电影详情信息
	@RequestMapping("movie/manage/showMovieDetail/{movieId}")
	public SysResult showMovieDetail(@PathVariable Integer movieId){
		Movie movie=movieservice.showMovieDetail(movieId);
		if(movie==null){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", movie);
		}
	}
	
	//获取热映的六部电影
	@RequestMapping("movie/manage/getHotMovie")
	public SysResult getHotMovie(){
		List<Movie> hotmovie=movieservice.getHotMovie();
		if(hotmovie.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", hotmovie);
		}
	}
	
	//根据演员名称查到演员ID
	@RequestMapping("movie/manage/getActorId/{ActorName}")
	public SysResult getActorId(@PathVariable String ActorName){
		Integer actorId=movieservice.getActorId(ActorName);
		if(actorId==null){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok",actorId);
		}
	}
	
}
