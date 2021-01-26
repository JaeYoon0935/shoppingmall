package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Category;
import com.shoppingmall.example.mapper.CategoryMapper;


@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categorymapper;
	
	@Override
	public List<Category> readCategory() {
		return categorymapper.readCategory();
	};

	@Override
	public List<Category> readCategory_name(){
		return categorymapper.readCategory_name();
	};
	
	@Override
	public int findCg_id(String name){
		return categorymapper.findCg_id(name);
	};
	
	@Override
	public void categoryUpdate(Category category) {
		categorymapper.categoryUpdate(category);
	};

	
	@Override
	public int nextCategory(Category category) {
//		int next = categorymapper.nextCategory(category); 이런식으로 짜면 됨.
//		category.setCg_id(next);
		return categorymapper.nextCategory(category);
	};
	
	@Override
	public void categoryAdd(Category category) {
		//mapper 2개를 사용한다. 
		//1개는 최대값에 +1을 해서 반환하는 mapper이고,
		//1개는 새로운 분류명과 반환된 값을 조합해서 레코드로 만드는 mapper이다. 이때 수량은 0으로 해주도록 한다.
		int next = categorymapper.nextCategory(category); 
		category.setCg_id(next);
		categorymapper.categoryAdd(category);
	};
	
	/*서비스에 mapper 2개를 쓰는 이유는 예를들어 금융권에서 서비스 하나에 절차를 수행하기 위해서 2개의 mapper가 필요하다면
	mapper 2개를 넣고, 2개의 mapper 중에 1개라도 오류가 나면 rollback을 해줘야 한다. 
	이러한 기능을 트랜잭션이라고 부른다. 
	또한 포인트 관련기능해서도 ~는 됬는데 포인트 차감에서 오류가 났다. 그러면 둘 중에 1개라도 오류가 났기 때문에 rollback을 해줘야 한다.
	이것이 트랜잭션이며 이러한 기능을 위해서 트랜잭션을 사용하는 것이다. 
	 */ 
	
	
	
}
