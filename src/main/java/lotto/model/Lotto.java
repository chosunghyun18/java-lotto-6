package lotto.model;

import java.util.List;


public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        LottoValidator lottoValidator = new LottoValidator();
        lottoValidator.validateLotto(numbers);
    }

    public LottoResult matchUp(Lotto givenLotto) {
        int countBall = (int)numbers.stream()
                .filter(givenLotto::haveSameBall)
                .count();
        return LottoResult.getResultByNumberOfBall(countBall);
    }

    public LottoResult matchUp(Lotto answerLotto, Integer bonusNumber) {
        LottoResult result = this.matchUp(answerLotto);
        if (wonFiveBonusNumber(result, bonusNumber)) return LottoResult.FIVE_PLUS_BONUS;
        return result;
    }

    private boolean haveSameBall(Integer number) {
        return this.numbers.contains(number);
    }

    private boolean wonFiveBonusNumber(LottoResult result, Integer bonusNumber) {
        return result.equals(LottoResult.FIVE_MATCHES) && haveSameBall(bonusNumber);
    }
}
