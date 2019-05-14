package com.mymovieapi.mymovieapi;

import com.mymovieapi.mymovieapi.models.tvshow.TvShow;
import com.mymovieapi.mymovieapi.services.MovieService;
import com.mymovieapi.mymovieapi.services.TvShowService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MymovieapiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =  SpringApplication.run(MymovieapiApplication.class, args);
		/*MovieService movieService = applicationContext.getBean(MovieService.class);
		movieService.getMoviesFromTmdb();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TvShowService tvShowService = applicationContext.getBean(TvShowService.class);
		tvShowService.getTvShowsFromTmdb();*/
	}
}
