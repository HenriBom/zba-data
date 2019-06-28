package org.zenika.zba.zbadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zenika.zba.zbadata.config.H2JpaConfig;
import org.zenika.zba.zbadata.controller.RecipeController;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.StepDao;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.step.Sanitizing;

import javax.transaction.Transactional;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(
        classes = { H2JpaConfig.class })
@Transactional
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class RecipeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeDao recipeDao;

    @Mock
    private StepDao stepDao;

    @InjectMocks
    RecipeController recipeController;

    private Recipe recipe1, recipe2;

    private Sanitizing step1, step2;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).alwaysDo(MockMvcResultHandlers.print()).build();

        recipe1 = new Recipe();
        recipe2 = new Recipe();

        recipe1.setId(1);
        recipe1.setName("Name1");
        recipe1.setIngredientType("Blonde");
        recipe1.setMalt("Malte");
        recipe1.setCreator("Creator1");
        recipe1.setSteps(asList(step1,step2));

        recipe2.setId(2);
        recipe2.setName("Name2");
        recipe2.setIngredientType("Blonde");
        recipe2.setMalt("Malte");
        recipe2.setCreator("Creator2");

        step1.setId(1);
        step1.setSelectedStep(1);

        step2.setId(2);
        step2.setSelectedStep(1);
    }

    @Test
    public void getAllRecipe_expects200And2Element() throws Exception {

        given(this.recipeDao.findAll()).willReturn(asList(recipe1, recipe2));

        this.mockMvc.perform(get("/recipes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].name").value("Name1"));
            verify(recipeDao, times(1)).findAll();
            verifyNoMoreInteractions(recipeDao);
    }

    @Test
    public void getStepsById_expects200And2Element() throws Exception {

        //given(this.stepDao.findByRecipeId(Long.valueOf(1))).willReturn(asList(step1, step2));

        this.mockMvc.perform(get("/recipe/1/steps")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].selectedStep").value(1));
        //verify(stepDao, times(1)).findByRecipeId(Long.valueOf(1));
        verifyNoMoreInteractions(stepDao);
    }

    @Test
    public void getStepsById_expects404() throws Exception {

        //given(this.stepDao.findByRecipeId(Long.valueOf(3))).willReturn(null);

        this.mockMvc.perform(get("/recipe/3/steps")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRecipeById_expects200AndDeleted() throws Exception {

        given(this.recipeDao.findByName("Name1")).willReturn(recipe1);

        this.mockMvc.perform(delete("/recipe/Name1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Deleted"));
        verify(recipeDao, times(1)).delete(recipe1);
        verify(recipeDao, times(1)).findByName("Name1");
        verifyNoMoreInteractions(recipeDao);
    }

    @Test
    public void deleteRecipeById_expects404() throws Exception {

        given(this.recipeDao.findByName("Name3")).willReturn(null);

        this.mockMvc.perform(delete("/recipe/Name3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void postRecipe_expects200AndLongId() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        //given(this.save.mainSave(mapper.writeValueAsString(recipe1))).willReturn(1L);

        this.mockMvc.perform(post("/recipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(recipe1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").value(1L));
    }

    @Ignore
    @Test
    public void putRecipe_expects200AndLongId() throws Exception {

        //given(this.save.mainSave("recipe1")).willReturn(1L);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(step1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1L));
    }
}

/**
question V1 is not working :

@ContextConfiguration(
        classes = { H2JpaConfig.class })
@Transactional
@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeDao recipeDao;

    @MockBean
    private Set<RecipeStep> recipeStep;

    private Recipe recipe1, recipe2;

    @Before
    public void setup() {

        recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("Name1");
        recipe1.setIngredientType("Blonde");
        recipe1.setMalt("Malte");
        recipe1.setCreator("Creator1");
        recipe1.setStep(recipeStep);

        recipe2 = new Recipe();
        recipe2.setId(2);
        recipe2.setName("Name2");
        recipe2.setIngredientType("Blonde");
        recipe2.setMalt("Malte");
        recipe2.setCreator("Creator2");
        recipe2.setStep(recipeStep);

    }

    @Test
    public void shouldFindAllRecipe() throws Exception {

        given(this.recipeDao.findAll()).willReturn(asList(recipe1,recipe2));

        this.mockMvc.perform(get("/Recipe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Size").value(2))
                .andExpect((jsonPath("$.[0].name").value("Name1")));

    }
}
**/