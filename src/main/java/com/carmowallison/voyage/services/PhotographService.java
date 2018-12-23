package com.carmowallison.voyage.services;

import com.carmowallison.voyage.domain.Category;
import com.carmowallison.voyage.domain.Photograph;
import com.carmowallison.voyage.dto.PhotographDTO;
import com.carmowallison.voyage.repositoties.PhotographRepository;
import com.carmowallison.voyage.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PhotographService {

	@Autowired
	private PhotographRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

	public List<Photograph> findAll() {
		return repository.findAll();
	}

	public Photograph findById(Integer id) {
		Optional<Photograph> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

    public Photograph insert(Photograph obj) {
        return repository.save(obj);
    }

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Photograph update(Photograph obj)  {
		Photograph newObj = findById(obj.getId());
		updateData(newObj, obj);
        return repository.save(newObj);
	}

	private void updateData(Photograph newObj, Photograph obj) {
		newObj.setActive(obj.isActive());

		if (obj.getTitle() != null) {
			newObj.setTitle(obj.getTitle());
		}
		if (obj.getDescription() != null) {
			newObj.setDescription(obj.getDescription());
		}
	}

	public Photograph fromDTO(PhotographDTO objDTO) {
		return new Photograph(objDTO.getId(), objDTO.getTitle(),objDTO.getDescription(), true,objDTO.getCategory(),objDTO.getLatitude(),objDTO.getLongitude());
	}

    public Page<Photograph> findPage(
            String title,List<Integer> categories,
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Category> categorias = categoryService.findAllByIds(categories);
        if(categorias.isEmpty()){
            return repository.findByTitleContaining(title,pageRequest);
        }
        return repository.findByTitleContainingAndCategoryIn(title,categorias,pageRequest);
    }
}
