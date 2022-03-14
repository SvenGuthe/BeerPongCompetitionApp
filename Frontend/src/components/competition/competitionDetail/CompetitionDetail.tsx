import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addCompetition, removeCompetitionDetail, storeCompetitionDetail } from "../../../store/competition/competition-store";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import CompetitionAdminDetail from "./competitionAdmin/CompetitionAdminDetail";
import CompetitionDetailTable from "./CompetitionDetailTable";
import CompetitionStatusAddRow from "./competitionStatus/CompetitionStatusAddRow";
import CompetitionTeamDetail from "./competitionTeam/CompetitionTeamDetail";

const CompetitionDetail: React.FC = (props) => {

    const dispatch = useDispatch();
    const id = useParams().id;

    const { competitionDetail } = useSelector((state: RootState) => {
        return {
            competitionDetail: state.competition.competitionDetail
        }
    });

    const competitionStatus = useMemo(() => {
        return competitionDetail?.competitionStatus;
    }, [competitionDetail]);

    const competitionAdminStatus = useMemo(() => {
        return competitionDetail?.competitionAdmins;
    }, [competitionDetail])

    const competitionTeamDetails = useMemo(() => {
        return competitionDetail?.competitionTeams;
    }, [competitionDetail])

    useEffect(() => {

        if (id) {
            dispatch(getRequestWithID(+id, "/competition/competition", [addCompetition, storeCompetitionDetail]));
        }

        return () => {
            dispatch(removeCompetitionDetail());
        };

    }, [id, dispatch]);

    return <>
        {competitionDetail && <>
            <CompetitionDetailTable competition={competitionDetail} />
            {competitionStatus && <TableSection>
                <h3>Turnier Status</h3>
                <EnumTable enumData={competitionStatus.map(singleCompetitionStatus => {

                    const additionalAttributes = [
                        {
                            id: singleCompetitionStatus.id + "_validFrom",
                            value: singleCompetitionStatus.validFrom
                        },
                        {
                            id: singleCompetitionStatus.id + "_validTo",
                            value: singleCompetitionStatus.validTo
                        }
                    ]

                    const newCompetitionStatus = {
                        ...singleCompetitionStatus,
                        additionalAttributes: additionalAttributes
                    }

                    return newCompetitionStatus;

                })} wrapped addRow={<CompetitionStatusAddRow />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {competitionAdminStatus && <TableSection>
                <h3>Turnier Admins</h3>
                {competitionAdminStatus.map(singleCompetitionAdminStatus => {
                    return <TableSection key={singleCompetitionAdminStatus.id}>
                        <CompetitionAdminDetail competitionAdminDetail={singleCompetitionAdminStatus} />
                    </TableSection>
                })}
            </TableSection>}
            {competitionTeamDetails && <TableSection>
                <h3>Turnier Teams</h3>
                {competitionTeamDetails.map(competitionTeamDetail => {
                    return <TableSection key={competitionTeamDetail.id}>
                        <CompetitionTeamDetail competitionTeamDetail={competitionTeamDetail} />
                    </TableSection>
                })}
            </TableSection>}
        </>}
    </>;

};

export default CompetitionDetail;