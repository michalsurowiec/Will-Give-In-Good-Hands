package pl.coderslab.charity.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category categoryEach : categoryRepository.findAll()){
            categoryDtoList.add(new CategoryDto(categoryEach));
        }
        return categoryDtoList;
    }
}
