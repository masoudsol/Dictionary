package com.dictionary;

import com.dictionary.modules.models.DefinitionModel;
import com.dictionary.modules.models.DefinitionsModel;
import com.dictionary.modules.repository.Repository;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testSort() {
        Repository repo = Repository.getInstance();

        List<DefinitionModel> list = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<10;i++) {
            DefinitionModel definitionModel = new DefinitionModel();

            definitionModel.setThumbs_up(random.nextInt(10000));
            definitionModel.setThumbs_down(random.nextInt(10000));
            definitionModel.setWord("meh");
            list.add(definitionModel);
        }

        DefinitionsModel definitionsModel = new DefinitionsModel();
        definitionsModel.setList(list);

        repo.setDefinitionsModel(definitionsModel);
        repo.sort(true);
        assertTrue(repo.getDefinitions().get(0).getThumbs_up() > repo.getDefinitions().get(9).getThumbs_up());

        repo.sort(false);
        assertTrue(repo.getDefinitions().get(1).getThumbs_down() > repo.getDefinitions().get(8).getThumbs_down());
    }
}