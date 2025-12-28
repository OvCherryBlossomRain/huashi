package cn.edu.scnu.actor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.actor.service.ActorService;
import cn.edu.scnu.pojo.Actor;
import cn.edu.scnu.pojo.Movie;
import cn.edu.scnu.vo.SysResult;

@RestController
public class ActorController {
	@Autowired
	ActorService actorservice;
	
	//得到演员总数所需分页的页数，  每页12个
	@RequestMapping("/actor/manage/getTotalNum")
	public SysResult getTotalNum(){
		Integer a=actorservice.getTotalNum();
		Integer extra=a%12;
		Integer pagenum=a/12+(extra>0?1:0);
		if(a>0){
			return SysResult.build(200, "ok", pagenum);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	
	//分页展示演员
	@RequestMapping("/actor/manage/showActor")
	public SysResult showActorByPage(Integer start, Integer length){
		List<Actor> actors=actorservice.showActorByPage(start,length);
		if(actors.isEmpty()){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", actors);
		}
	}
	
	//根据演员id返回演员具体信息
	@RequestMapping("/actor/manage/showActorDetail/{actorId}")
	public SysResult showActorDetail(@PathVariable Integer actorId){
		Actor actor=actorservice.showActorDetail(actorId);
		if(actor==null){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", actor);
		}
	}
	
	//根据演员名称查到到他所演的电影
	@RequestMapping("/actor/manage/getActorMovie/{actorName}")
	public SysResult getActorMovie(@PathVariable String actorName){
		List<Movie> movies=actorservice.getActorMovie(actorName);
		if(movies.size()==0){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", movies);
		}
	}
	
}
