import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { billingStatusHierarchy, homeHierarchy } from "../../utility/hierarchy";
import { billingStatusRoute } from "../../api-routes/competition";
import { storeBillingStatus } from "../../store/competition/competition-store";

const BillingStatus = () => {

    const billingStatus = useSelector((state: RootState) => {
        return state.competition.billingStatus;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, billingStatusHierarchy]} />
        <h2>Zahlungs Status</h2>
        <EnumOverview url={billingStatusRoute} storeFunction={storeBillingStatus} paginationData={billingStatus} />
    </>

};

export default BillingStatus;