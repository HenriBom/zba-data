package org.zenika.zba.zbadata;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.zenika.zba.zbadata.config.H2JpaConfig;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.model.Recipe;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { H2JpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DataJpaTest
@EntityScan( basePackages = {"org.zenika.zba.zbadata"})
@Ignore
public class RecipeDaoTest {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByName_thenReturnRecipe() {
        // given
        Recipe toFind = new Recipe();
        entityManager.persist(toFind);
        entityManager.flush();
        // when
        Recipe found = recipeDao.findByName(toFind.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(toFind.getName());
    }
}