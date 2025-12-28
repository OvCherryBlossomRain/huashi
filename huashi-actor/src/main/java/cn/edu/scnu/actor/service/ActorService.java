package cn.edu.scnu.actor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.actor.mapper.ActorMapper;
import cn.edu.scnu.pojo.Actor;
import cn.edu.scnu.pojo.Movie;

@Service
public class ActorService {
	
	@Autowired
	ActorMapper actormapper;

	public Integer getTotalNum() {
		// TODO Auto-generated method stub
		return actormapper.getTotalNum();
	}

	public List<Actor> showActorByPage(Integer start, Integer length) {
		// TODO Auto-generated method stub
		return actormapper.showActorByPage(start,length);
	}

	public Actor showActorDetail(Integer actorId) {
		// TODO Auto-generated method stub
		return actormapper.showActorDetail(actorId);
	}

	public List<Movie> getActorMovie(String actorName) {
		// TODO Auto-generated method stub
		List<String> movieName=actormapper.getMovieName(actorName);
		List<Movie> movies=new ArrayList<Movie>();
		for(int i=0;i<movieName.size();i++){
			movies.add(actormapper.getMovie(movieName.get(i)));
		}
		return movies;
	}
	
	
}
