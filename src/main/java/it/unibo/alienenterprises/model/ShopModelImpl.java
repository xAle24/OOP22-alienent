package it.unibo.alienenterprises.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.ShopModel;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * ShopModelImpl.
 */
public class ShopModelImpl implements ShopModel {

    private final Set<PowerUp> powerUps = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> check(final String id, final UserAccountImpl user) {

        final Iterator<PowerUp> pwuIterator = powerUps.iterator();
        while (pwuIterator.hasNext()) {
            final PowerUp currPwu = pwuIterator.next();

            if (currPwu.getId().equals(id)) {
                return user.getMoney() - currPwu.getCost() > 0 ? Optional.of(-currPwu.getCost())
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
    public void updateShop(final String id, final UserAccountImpl user, final int changeMoney) {
        user.updateInventory(id);
        user.setMoney(changeMoney);
        user.equals(updateToAddPwu(id, user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadPwu(Set<PowerUp> pwu) {
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
        return user;
    }

    @Override
    public Set<PowerUp> getPwu() {
        return this.powerUps;
    }

}
