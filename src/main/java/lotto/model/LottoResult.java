package lotto.model;

public enum LottoResult {
    UNDER_THREE(0, "2개 이하 일치",0),
    THREE_MATCHES(5000, "3개 일치 (5,000원)",3),
    FOUR_MATCHES(50000, "4개 일치 (50,000원)",4),
    FIVE_MATCHES(1500000, "5개 일치 (1,500,000원)",5),
    FIVE_PLUS_BONUS(30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)",7),
    SIX_MATCHES(2000000000, "6개 일치 (2,000,000,000원)",6);
    private final Integer prizeAmount;
    private final String message;
    private final Integer ballCount;

    LottoResult(Integer prizeAmount, String message,Integer ballCount) {
        this.prizeAmount = prizeAmount;
        this.message = message;
        this.ballCount = ballCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public String getMessage() {
        return message;
    }
    public static LottoResult matchUp(Integer countBalls, Lotto userLotto ,Integer bonusNumber) {
        LottoResult re = getResultByNumberOfBall(countBalls);
        return checkBonusNumber(re,userLotto,bonusNumber);
    }
    public static LottoResult checkBonusNumber(LottoResult result, Lotto given ,Integer bonusNumber) {
        if(result.equals(LottoResult.FIVE_MATCHES) && given.haveSameBall(bonusNumber))return LottoResult.FIVE_PLUS_BONUS;
        return result;
    }
    public static LottoResult getResultByNumberOfBall(Integer countBall) {
        if (countBall.equals(THREE_MATCHES.ballCount))return THREE_MATCHES;
        if (countBall.equals(FOUR_MATCHES.ballCount)) return FOUR_MATCHES;
        if (countBall.equals(FIVE_MATCHES.ballCount)) return FIVE_MATCHES;
        if (countBall.equals(SIX_MATCHES.ballCount)) return SIX_MATCHES;
        return UNDER_THREE;
    }
}
