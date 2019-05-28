package org.zenika.zba.zbadata;

import com.mysql.cj.xdevapi.JsonString;
import org.json.JSONObject;
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
import org.zenika.zba.zbadata.controller.recipe.Save;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.RecipeStepDao;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.RecipeStep;
import org.zenika.zba.zbadata.model.step.Sanitizing;
import javax.transaction.Transactional;
import java.util.Set;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(
        classes = { H2JpaConfig.class })
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeDao recipeDao;

    @Mock
    private RecipeStepDao recipeStepDao;

    @Mock
    private Set<RecipeStep> recipeStepSet;

    @Mock
    private Save save;

    @InjectMocks
    RecipeController recipeController;

    private Recipe recipe1, recipe2;

    private Sanitizing step1, step2;

    private RecipeStep recipeStep1, recipeStep2;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).alwaysDo(MockMvcResultHandlers.print()).build();

        recipe1 = new Recipe();
        recipe2 = new Recipe();
        step1 = new Sanitizing();
        step2 = new Sanitizing();
        recipeStep1 = new RecipeStep();
        recipeStep2 = new RecipeStep();

        recipeStepSet.add(recipeStep1);
        recipeStepSet.add(recipeStep2);

        recipe1.setId(1);
        recipe1.setName("Name1");
        recipe1.setIngredientType("Blonde");
        recipe1.setMalt("Malte");
        recipe1.setCreator("Creator1");

        recipe2.setId(2);
        recipe2.setName("Name2");
        recipe2.setIngredientType("Blonde");
        recipe2.setMalt("Malte");
        recipe2.setCreator("Creator2");

        step1.setId(1);
        step1.setSelectedStep(1);

        step2.setId(2);
        step2.setSelectedStep(1);

        recipeStep1.setId(Long.valueOf(1));
        recipeStep1.setRecipe(recipe1);
        recipeStep1.setStep(step1);
        recipeStep1.setStepNumber(1);

        recipeStep2.setId(Long.valueOf(1));
        recipeStep2.setRecipe(recipe2);
        recipeStep2.setStep(step2);
        recipeStep2.setStepNumber(2);
    }

    @Ignore
    @Test
    public void getAllRecipe_expects200And2Element() throws Exception {

        given(this.recipeDao.findAll()).willReturn(asList(recipe1, recipe2));

        this.mockMvc.perform(get("/Recipe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].name").value("Name1"));
            verify(recipeDao, times(1)).findAll();
            verifyNoMoreInteractions(recipeDao);
    }

    @Test
    public void getStepsById_expects200And2Element() throws Exception {

        given(this.recipeStepDao.findByRecipeId(Long.valueOf(1))).willReturn(asList(recipeStep1, recipeStep2));

        this.mockMvc.perform(get("/Steps1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].selectedStep").value(1));
        verify(recipeStepDao, times(1)).findByRecipeId(Long.valueOf(1));
        verifyNoMoreInteractions(recipeStepDao);
    }

    @Test
    public void getStepsById_expects404() throws Exception {

        given(this.recipeStepDao.findByRecipeId(Long.valueOf(3))).willReturn(null);

        this.mockMvc.perform(get("/Steps1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRecipeById_expects200AndDeleted() throws Exception {

        given(this.recipeDao.findByName("Name1")).willReturn(recipe1);

        this.mockMvc.perform(delete("/RecipeName1")
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

        this.mockMvc.perform(delete("/RecipeName3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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