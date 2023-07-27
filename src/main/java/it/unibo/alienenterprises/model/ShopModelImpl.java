package it.unibo.alienenterprises.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.alienenterprises.controller.api.ShopController;
import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * ShopModelImpl.
 */
public class ShopModelImpl implements ShopModel {

    private ShopController controller;
    private final List<PowerUp> powerUps = new LinkedList<>();

    public ShopModelImpl(ShopController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> check(final String id) {

        UserAccountImpl user = controller.getUserAccount();

        final Iterator<PowerUp> pwuIterator = powerUps.iterator();
        while (pwuIterator.hasNext()) {
            final PowerUp currPwu = pwuIterator.next();

            if (currPwu.getId().equals(id)) {
                return user.getMoney() - currPwu.getCost() >= 0 ? Optional.of(-currPwu.getCost())
                        : Optional.empty();
            }
            // se returna i soldi al negativo io posso toglierli con updateMoney
            // non fai il controllo se si può comprare perchè il bottone si disattiva
            // automaticamente
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateShop(final String id, final int changeMoney) {
        UserAccountImpl user = controller.getUserAccount();
        user.updateInventory(id);
        user.setMoney(changeMoney);
        updateToAddPwu(id, user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwu(List<PowerUp> pwu) {
        this.powerUps.addAll(pwu);
    }

    private UserAccountImpl updateToAddPwu(final String id, final UserAccountImpl user) {
        final Iterator<PowerUp> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            final PowerUp curr = iterator.next();

            if (curr.getId().equals(id)) {
                final Map<Statistic, Integer> map = curr.getStatModifiers();
                user.updateToAddPwu(map);
            }
        }
        System.out.println(user.getInventory());
        System.out.println(user.getToAddPwu());
        return user;
    }

    @Override
    public List<PowerUp> getPwu() {
        return this.powerUps;
    }

}
