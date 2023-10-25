package br.ufms.gabriel.costa.silva.patrimonio.ui.objetos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObjetosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ObjetosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Objetos do Patrimponio");
    }

    public LiveData<String> getText() {
        return mText;
    }
}