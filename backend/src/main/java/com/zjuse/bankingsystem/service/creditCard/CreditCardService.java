package com.zjuse.bankingsystem.service.creditCard;

import com.zjuse.bankingsystem.entity.creditCard.CreditCard;
import com.zjuse.bankingsystem.entity.creditCard.CreditCardAdmin;
import com.zjuse.bankingsystem.entity.creditCard.CreditCardApplication;
import com.zjuse.bankingsystem.entity.creditCard.CreditCardInspector;
import com.zjuse.bankingsystem.mapper.creditCard.CreditCardMapper;
import com.zjuse.bankingsystem.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.PrimitiveIterator;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardMapper creditCardMapper;

    public ApiResult getCardsByIdNumber(String idNumber) {
        return new ApiResult(true, null, creditCardMapper.queryCards(idNumber));
    }

    public ApiResult addNewCreditCardRequest(String idNumber, BigInteger cardLimit, String password) {
        creditCardMapper.addNewCreditCardRequest(idNumber, cardLimit, password);
        return new ApiResult(true, null, null);
    }

    public ApiResult modifyCreditCardPassword(BigInteger cardId, String password) {
        creditCardMapper.modifyCreditCardPassword(cardId, password);
        return new ApiResult(true, null, null);
    }

    public ApiResult addModifyLimitRequest(String idNumber, BigInteger cardId, BigInteger limit) {
        creditCardMapper.addModifyLimitRequest(idNumber, cardId, limit);
        return new ApiResult(true, null, null);
    }

    public ApiResult returnMoney(BigInteger cardId, BigInteger amount) {
        creditCardMapper.returnMoney(cardId, amount);
        return new ApiResult(true, null, null);
    }

    public ApiResult makeCreditCardLost(BigInteger cardId) {
        CreditCard creditCard = creditCardMapper.findCreditCard(cardId);
        if (creditCard.getId() == null) {
            return new ApiResult(false, "该信用卡不存在");
        }
        creditCardMapper.setCreditCardLost(cardId);
        creditCardMapper.insertCreditCard(creditCard);
        return new ApiResult(true, "挂失成功");
    }

    public ApiResult deleteCreditCard(BigInteger cardId) {
        creditCardMapper.deleteCreditCard(cardId);
        return new ApiResult(true, "注销信用卡成功");
    }

    public ApiResult loginAdmin(String name, String password) {
        CreditCardAdmin creditCardAdmin = creditCardMapper.loginAdmin(name, password);
        if (creditCardAdmin == null) {
            return new ApiResult(false, "登录失败");
        } else {
            return new ApiResult(true, "登录成功");
        }
    }

    public ApiResult queryInspectors() {
        List<CreditCardInspector> creditCardInspectors = creditCardMapper.queryInspectors();
        return new ApiResult(true, creditCardInspectors);
    }

    public ApiResult modifyInspectorPassword(Integer id, String password) {
        creditCardMapper.modifyInspectorPassword(id, password);
        return new ApiResult(true, "修改成功");
    }

    public ApiResult modifyInspectorLevel(Integer id, Integer permission) {
        creditCardMapper.modifyInspectorLevel(id, permission);
        return new ApiResult(true, "修改成功");
    }

    public ApiResult deleteInspector(Integer id) {
        creditCardMapper.deleteInspector(id);
        return new ApiResult(true, "删除成功");
    }

    public ApiResult addNewInspector(String name, String password, Integer permission) {
        creditCardMapper.addNewInspector(name, password, permission);
        return new ApiResult(true, "添加成功");
    }

    public ApiResult loginInspector(String name, String password){
        CreditCardInspector creditCardInspector = creditCardMapper.loginInspector(name, password);
        if(creditCardInspector == null){
            return new ApiResult(false,"登录失败");
        }else{
            return new ApiResult(true,creditCardInspector);
        }
    }

    public ApiResult queryRequestsByInspector(Integer permission){
        if(permission.equals(1)){
            List<CreditCardApplication> creditCardApplications = creditCardMapper.queryPartRequestByInspector();
            return new ApiResult(true,creditCardApplications);
        }else{
            List<CreditCardApplication> creditCardApplications = creditCardMapper.queryAllRequestByInspector();
            return new ApiResult(true,creditCardApplications);
        }
    }
}
