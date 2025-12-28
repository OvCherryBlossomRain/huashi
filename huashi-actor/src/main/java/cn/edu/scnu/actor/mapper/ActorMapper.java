package cn.edu.scnu.actor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.scnu.pojo.Actor;
import cn.edu.scnu.pojo.Movie;

public interface ActorMapper {

	Integer getTotalNum();

	List<Actor> showActorByPage(@Param("start")Integer start,@Param("length") Integer length);

	Actor showActorDetail(Integer actorId);

	List<String> getMovieName(String actorName);

	Movie getMovie(String movieName);
	
}
