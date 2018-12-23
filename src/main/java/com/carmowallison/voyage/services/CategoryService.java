package com.carmowallison.voyage.services;

import com.carmowallison.voyage.domain.Category;
import com.carmowallison.voyage.dto.CategoryDTO;
import com.carmowallison.voyage.repositoties.CategoryRepository;
import com.carmowallison.voyage.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Category insert(Category obj) {
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);

		return repository.save(newObj);
	}

	private void updateData(Category newObj, Category obj) {

		newObj.setActive(obj.isActive());

		if (obj.getName() != null) {
			newObj.setName(obj.getName());
		}
		if (obj.getName() != null) {
			newObj.setName(obj.getName());
		}
	}

	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName(), true);
	}

	public List<Category> findAllByIds(List<Integer> ids) {
		return repository.findByIdIn(ids);
	}
}
