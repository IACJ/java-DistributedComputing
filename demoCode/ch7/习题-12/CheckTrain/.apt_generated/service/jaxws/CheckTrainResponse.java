
package service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "checkTrainResponse", namespace = "http://service/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkTrainResponse", namespace = "http://service/")
public class CheckTrainResponse {

    @XmlElement(name = "return", namespace = "")
    private List<service.Train> _return;

    /**
     * 
     * @return
     *     returns List<Train>
     */
    public List<service.Train> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<service.Train> _return) {
        this._return = _return;
    }

}
