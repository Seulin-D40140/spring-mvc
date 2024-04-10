package fr.fms.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ArticleController 
{
	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping("/index")
	public String index(Model model , @RequestParam(name="page" , defaultValue = "0") int page,
									  @RequestParam(name="keyword" , defaultValue = "") String kw) 
	{
		Page<Article> articles = articleRepository.findByDescriptionContains(kw , PageRequest.of(page, 5));
		//Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
		model.addAttribute("listArticles" , articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage" , page);
		model.addAttribute("keyword" , kw); 
		return "articles" ;
	}
	@GetMapping("/delete")
	public String delete(Long id , int page , String keyword)
	{
		articleRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	
	@GetMapping("/article")
	public String article() 
	{
		return "article";
	}
	
	@PostMapping("/save")
	public String save(Article article) 
	{
		articleRepository.save(article);
		return "article";
	}
}
