package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Like;

public class LikeTest {

    @Test //check whether like info are correct
    void NewLikeTest()
    {
        Like l = new Like(1,2,3);

        assertEquals(2, l.getBookId());
        assertEquals(3, l.getUserId());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // book id zero
    void getBookIdWithZeroValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Book id must not be zero");

        Like l= new Like();
        l.setBookId(0);
    }

    @Test // user id zero
    void getUserIdWithZeroValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User id must not be zero");

        Like l= new Like();
        l.setUserId(0);
    }
}
