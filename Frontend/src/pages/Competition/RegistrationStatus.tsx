import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { homeHierarchy, registrationStatusHierarchy } from "../../types/hierarchy";
import { registrationStatusRoute } from "../../api-routes/competition";
import { storeRegistrationStatus } from "../../store/competition/competition-store";

const RegistrationStatus = () => {

    const registrationStatus = useSelector((state: RootState) => {
        return state.competition.registrationStatus;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, registrationStatusHierarchy]} />
        <h2>Registrations Status</h2>
        <EnumOverview url={registrationStatusRoute} storeFunction={storeRegistrationStatus} data={registrationStatus} />
    </>

};

export default RegistrationStatus;