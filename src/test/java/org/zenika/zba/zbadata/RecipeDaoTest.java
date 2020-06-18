package org.zenika.zba.zbadata;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
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
        classes = { H2JpaConfig.class })
@Transactional
@DataJpaTest
@TestComponent
@ComponentScan("src.main.java.org.zenika.zba.zbadata")
@EntityScan( basePackages = {"src.main.java.org.zenika.zba.zbadata.*"})
@EnableJpaRepositories(basePackages = {"src.main.java.org.zenika.zba.zbadata.*"})
@Ignore
public class RecipeDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired(required = true)
    private RecipeDao recipeDao;

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