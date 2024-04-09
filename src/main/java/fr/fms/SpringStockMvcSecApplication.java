package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@SpringBootApplication
public class SpringStockMvcSecApplication  implements CommandLineRunner
{
	@Autowired
	ArticleRepository articleRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		articleRepository.save(new Article("samsung" , 250));
		articleRepository.save(new Article("sony" , 450));
		articleRepository.save(new Article("lg" , 350));
		articleRepository.save(new Article("phillips" , 150));
		articleRepository.save(new Article("samsung" , 250));
		articleRepository.save(new Article("sony" , 450));
		articleRepository.save(new Article("lg" , 350));
		articleRepository.save(new Article("phillips" , 150));
		articleRepository.save(new Article("samsung" , 250));
		articleRepository.save(new Article("sony" , 450));
		articleRepository.save(new Article("lg" , 350));
		articleRepository.save(new Article("phillips" , 150));
		
		System.out.println("---------------------------------------------------------------------");
		articleRepository.findAll().forEach(a -> System.out.println(a));
		System.out.println("---------------------------------------------------------------------");
	}

}
