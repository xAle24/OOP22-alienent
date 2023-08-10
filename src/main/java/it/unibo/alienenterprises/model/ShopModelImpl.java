package it.unibo.alienenterprises.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.UserAccount;

/**
 * The implementation of ShopModel.
 * 
 * @author Ginevra Bartolini
 */
public class ShopModelImpl implements ShopModel {

    private final Controller controller;
    private final List<PowerUp> powerUps = new LinkedList<>();

    /**
     * This constructor ensures ShopModel has always a ShopController that he can
     * reference.
     * 
     * @param controller
     */
    public ShopModelImpl(final Controller controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwu(final List<PowerUp> pwu) {
        this.powerUps.addAll(pwu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> check(final String id) {

        final UserAccount user = controller.getUserAccount();
        return powerUps.stream().filter(p -> p.getId().equals(id))
                .filter(p -> (user.getMoney()
                        - (p.getCost()) * (controller.getUserAccount().getCurrLevel(id) + 1)) >= 0)
                .map(p -> -(p.getCost()) * (controller.getUserAccount().getCurrLevel(id) + 1)).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateShop(final String id, final int changeMoney) {
        final UserAccount user = controller.getUserAccount();
        user.updateInventory(id);
        user.setMoney(changeMoney);
        updateToAddPwu(id, user);
    }

    private UserAccount updateToAddPwu(final String id, final UserAccount user) {

        user.updateToAddPwu(
                powerUps.stream().filter(p -> p.getId().equals(id)).map(p -> p.getStatModifiers()).findFirst().get());
        return user;
    }

}
