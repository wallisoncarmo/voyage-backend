package com.carmowallison.voyage.resources;

import com.carmowallison.voyage.domain.Photograph;
import com.carmowallison.voyage.dto.PhotographDTO;
import com.carmowallison.voyage.services.ImageService;
import com.carmowallison.voyage.services.PhotographService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/photograph")
public class PhotographResource {

	@Autowired
	private PhotographService service;

    @Autowired
    private ImageService imagemService;

	@ApiOperation(value = "Busca todas as fotos")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PhotographDTO>> findAll() {
		List<Photograph> list = service.findAll();
		List<PhotographDTO> listDTO = list.stream().map(obj -> new PhotographDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(value = "Busca por uma foto pelo seu id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PhotographDTO> findById(@PathVariable Integer id) {
		Photograph obj = service.findById(id);
		return ResponseEntity.ok().body(new PhotographDTO(obj));
	}

	@ApiOperation(value = "insere uma nova foto")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PhotographDTO objDTO) {
		Photograph obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza os dados da foto")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody PhotographDTO objDTO) {
		Photograph obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deleta uma foto")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

    @ApiOperation(value = "Salva uma foto no servidor")
	@RequestMapping(value = "/upload/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(value = "file") MultipartFile file,@PathVariable Integer id) throws IOException {
	    imagemService.uploadProfilePicture(file,id);
		return ResponseEntity.ok().build();
	}


    @ApiOperation(value = "Busca todas as fotos e separa por paginação")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Photograph>> findPage(
                                                    @RequestParam(value = "categories", defaultValue = "") List<Integer> categories,
                                                    @RequestParam(value = "title", defaultValue = "") String title,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "title") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Photograph> list = service.findPage(title,categories,page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

}
