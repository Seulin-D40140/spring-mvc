package fr.fms.web;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import javassist.expr.NewArray;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
	public String article(Model model) 
	{
		model.addAttribute("article", new Article());
		return "article";
	}
	
	@PostMapping("/save")
	public String save( @Valid Article article , BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) return "article";
		
		articleRepository.save(article);
		return "redirect:/index";
	}
	
	@GetMapping("/update")
	public String update(Model model , Long id)
	{
		Optional<Article> articleToUpdate = articleRepository.findById(id);
		Article article2 = articleToUpdate.orElse(null);
		model.addAttribute("article" ,article2);
		return "update";
	}
	
	@PostMapping("/toUpdate")
	public String toUpdate(@Valid Article upArticle , Long id) 
	{
		Optional<Article> optarticle = articleRepository.findById(id);
		Article article2 = optarticle.get();
		article2.setDescription(upArticle.getDescription());
		article2.setPrice(upArticle.getPrice());
		article2.setId(upArticle.getId());
		articleRepository.save(article2);
//		article2.setDescription(article.getDescription());
//		article2.setPrice(article.getPrice());
		return "redirect:/index";
	}
	
}
